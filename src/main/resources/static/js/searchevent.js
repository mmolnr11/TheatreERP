$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#search-event").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){
        var searchdatestart = $("#searchdatestart").val();
        var searchdateend = $("#searchdateend").val();
        var formData = {
            "searchdatestart": searchdatestart,
            "searchdateend": searchdateend
            // searchdatestart: searchdatestart,
            // searchdateend: searchdateend
        };

        console.log(formData);
        debugger;
        $.ajax({
            // contentType : "application/json",
            // dataType : 'json',
            url : "/event/search",
            method : "POST",
            data : formData,
            success : function(result) {
                debugger;

                // var obj = $.parseJSON(result);
                console.log(result);
                // if (Object.keys(result).length !== 0){
                //     $.each(result, function(i, string){
                //         var date = "Datumok " + i + ": " + string.title ;
                //         $('#searchDiv .list-group').append('<li><h4 class="list-group-item">'+date+'</h4></li>')
                //     });
                //     console.log(result)
                //     // }else {
                //     //
                //     // }
                //     alert("malacka");
                //
                //     console.log(result);
                // }
                // else {
                //     debugger;
                //     alert(Object.keys(result).length);
                //
                //     $('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+firstname +" "+  lastname +" sikeresen hozzadava"+'</h4></li>');
                // }
            },
            error : function(e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        resetData();

    }

    function resetData(){
        $("#employee").val("");
    }
})