$( document ).ready(function() {
    $("#selectEmployee").click(function () {

        var addButton = $("#selectEmployee");
        var selectedEmployee = $("#employee option:selected");
        var $numOfEmployee = $("#numOfEmployee");
        var newVal = parseInt($numOfEmployee.text() - 1);
        if (newVal !== 0) {

            if (selectedEmployee.val() !== "") {
                $numOfEmployee.text(newVal);

            }

        }
        else {
            addButton.hide();
            $numOfEmployee.text(0);
            $numOfEmployee.hide();
            $("#noValue").text("Létszám elérve");
            $("#noValue").show();
            $("#extraEmployee").show();


        }

        if (selectedEmployee.val() !== "") {
            $(".buttonpara").append(`<div><button name = "${selectedEmployee.text()}" class="button" id="${selectedEmployee.val()}">
                                            ${selectedEmployee.text()}
                                         </button></div>`);
        }

    })
});