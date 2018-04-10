

$(document).on("click","#deleteCommentButton",function (){
    var commentId = $( this ).attr("name");
    var parentTag = $( this ).parent().get( 0 ).tagName;
    var parentTag2 = $( this ).parent().closest( "div" );
    var parentTag3 = $( this ).closest( "div" );

    var commentDiv = $("#divComment");


    debugger;
    console.log(commentId);
    $.post("/deleteComment",
        {
            "commentId": commentId,
        },
        function(response){
            commentDiv.find(parentTag3).remove();
            //
            // commentDiv.find('button[name='+ commentId +']').remove();
            // commentDiv.find(parentTag).remove();
            // commentDiv.find(parentTag2).remove();
        });
});