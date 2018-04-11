// $( document ).ready(function() {
//
//     var url = window.location;

    // SUBMIT FORM
    // $("#addDates").submit(function(event) {
    //     // Prevent the form from submitting via the browser.
    //     event.preventDefault();
    //     ajaxPost();
    // });
    //
    //
    // function ajaxPost(){
        $(document).on("click","#addDateButton",function (){
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var dayOfEvent = $("#dayOfEvent").val();
        var eventId = $("#addDateButton").attr("name");
        debugger;
        var formData = {
            "startTime": startTime,
            "endTime": endTime,
            "dayOfEvent" : dayOfEvent,
            "eventId" : eventId
        };

        console.log(formData);
        $.ajax({
            // contentType : "application/json",
            // dataType : 'json',
            url : "/admin/event/addDate",
            method : "POST",
            data : formData,
            success : function(result) {


                console.log(result);
                if (Object.keys(result).length !== 0){
                    $.each(result, function(i, event){
                        // var startDate = new Date(event.startDateTime);
                        // var endDate = new Date(event.endDateTime);
                        // var eventId = event.id;
                        // var date = "Esemenyek : " + event.title + " " + startDate + " " +
                        //     endDate + " "+ event.description;
                        // var link = "<a href='/event/" + eventId +"/description'>This is the link</a>";

                        // $('#searchDiv .list-group').append('<li><a  href="user/event/'+ eventId + '/description"  class="list-group-item">'+date+'</a></li>')
                    });
                }
                else {
                    debugger;
                    alert(Object.keys(result).length);

                    $('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+firstname +" "+  lastname +" sikeresen hozzadava"+'</h4></li>');
                }
            },
            error : function(e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });


    });

    // });