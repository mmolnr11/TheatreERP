    $(document).on("click","#addCommentButton",function (){
        var comment = $( "input" ).val();
        var role = $("#addCommentButton").attr("name");
//            var date = new Date();
        var eventId = $("#divComment").attr("class");
debugger;
        console.log(comment);
        $( "#divComment" ).append( `<div><tr>
                <td>${comment}</td>
                <td>${role}</td>
                </tr>
                </div>` );
        $.post("/addComment",
            {
//                    name: $(this).attr("name"),
                "comment": comment,
                "role": role,
                "eventId": eventId
            },
            function(response){
                alert("Elmentve a komment: " + response);
            });
    });
