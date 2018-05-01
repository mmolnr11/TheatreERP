
    $(document).on("click","#addDateButton",function (){
        var title = $("#title").text();
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
            url : "/admin/event/addDate",
            method : "POST",
            data : formData,
            success : function(result) {


                console.log(result);
                if (Object.keys(result).length !== 0){
                    var start = new Date(result.startDate);
                    // TODO new url needs to be fixed it is
                    // http://localhost:9000/admin/event/2/admin/event/2/date/2
                    $("#datesToEvent").append('<a  href="admin/event/'+eventId+ "/date/"+ eventId + '"  ' +
                        'class="list-group-item">'+ title +" "+ dayOfEvent + " " +
                        startTime + ":00.0 "+
                        dayOfEvent + " " +
                        endTime +":00.0 "+ '</a></br>');
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

