
$(document).on("click","#extraEmployee",function (){

    let addButton = $("#selectEmployee");
    let $numOfEmployee = $("#numOfEmployee");

    var role = $("#addCommentButton").attr("name");
//            var date = new Date();
    var dateId = $("#divComment").attr("class");

    // let newVal = parseInt($numOfEmployee.text() + 1);
debugger;
    addButton.show();
    // let comment;
    let comment = prompt("Kérlek kommenteld");
    if (comment == null || comment == "") {
        // comment = "Sikertelen";
    } else {
        $( "#divComment" ).append( `<div><tr>
                <td>${comment}</td>
                <td>${role}</td>
                </tr>
                </div>` );
    }

    $.post("/addComment",
        {
//                    name: $(this).attr("name"),
            "comment": comment,
            "role": role,
            "dateId": dateId
        },
        function(response){
            // alert("Elmentve a komment: " + response);

        })
});


    // $numOfEmployee.hide();


