var nextPage = 1;
var totalPages;

$(document).on("click", function (){
    size = 12;
    sort = "id";
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ("/Recipes/getMoreRecipes?page=" + nextPage + "&size=" + size + "&sort=" + sort + ",desc"),
        success: function (result){
            $.each(result.content, function (value){
                $("body").append()
            });
        }
    });
});