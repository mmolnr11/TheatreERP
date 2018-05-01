// TODO
$( document ).ready(function() {
    $(document).on("click",".button",function (){
        debugger;
        var name = $( this ).attr("name");
        var $numOfEmployee = $("#numOfEmployee");
        var newVal = parseInt($numOfEmployee.text());
        newVal = newVal +1;
        $numOfEmployee.text( newVal );
        var dateId = $("#divComment").attr("class");
        var addButton = $("#selectEmployee");



        var empId = $( this ).attr("id");
        $.post("/restoreEmployee",
            {
                name: $(this).attr("name"),
//                    "name": $(this).attr("name"),
                "empId": $(this).attr("id"),
                "dateId": dateId
            },
            function(response){
                   // alert("Name: " + response);
            });
        $( this ).remove(),
                addButton.show();

        $("#noValue").hide();
        $numOfEmployee.show();

            $('#employee').append(`<option value="${empId}">${name}</option>`)


    });

});