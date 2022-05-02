var nextPage = 1;
var totalPages;
var recipeList = [];

$('.cargarRecetas').on("click", function (){
    $(".cargarRecetas").hide();
    var spinner = "<div class='spinner'></div>";
    $(".impresion").append(spinner);
    setTimeout(   function()    {
    console.log("Entro")
    size = 12;
    sort = "id";
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ("/getMoreRecipes?page=" + nextPage + "&size=" + size + "&sort=" + sort + ",desc"),
        success: function (result){
            console.log(result)
            $(".spinner").remove();
            $.each(result.content, function (id,value){

                console.log(value)
                var base = "<div class='col-sm-3 space_all prueba'>";
                base = base.concat("<div class='bp_2m clearfix'>");
                base = base.concat("<div class='cuadro_intro_hover' style='background-color:#cccccc; height:250px;'>");
                base = base.concat("<img style='height:250px;width: 300px;'src='/recipe/"+ value.id +"/image' class='fotoReceta' alt='abc'>");
                base = base.concat("<div class='caption'>");
                base = base.concat("<div class='blur'></div>");
                base = base.concat("<div class='caption-text'>");
                base = base.concat("<h3 style='border-top:2px solid white; padding-top:20px;'><a href='/Recipe/"+ value.id +"'>" + value.name + "</a></h3>");
                base = base.concat("<p style='margin-top:50px;'><a href='/Recipe/"+ value.id +"'>Show Recipe</a></p>");
                base = base.concat("</div>");
                base = base.concat("</div>");
                base = base.concat("</div>");
                base = base.concat("</div>");
                base = base.concat("</div>");

                $(".impresionRecipes").append(base);


            });
            if (nextPage + 1 < result.totalPages) {
                $(".cargarRecetas").show();
                nextPage++;
            }else{
                $(".loadMoreButton").remove();
            }
        }
    });
    }, 3500);
});