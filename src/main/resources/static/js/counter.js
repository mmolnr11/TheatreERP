$( document ).ready(function() {
    $("#selectEmployee").click(function () {
        debugger;
        var selectedEmployee = $("#employee option:selected");
        var $numOfEmployee = $("#numOfEmployee");
        var newVal = parseInt($numOfEmployee.text() - 1);
        if (newVal !== 0) {
            if (selectedEmployee.val() !== "") {
                $numOfEmployee.text(newVal);
            }

        } else {
            $numOfEmployee.text("Letszam elerve");


        }

        if (selectedEmployee.val() !== "") {
            $(".buttonpara").append(`<div><button name = "${selectedEmployee.text()}" class="button" id="${selectedEmployee.val()}">
                                            ${selectedEmployee.text()}
                                         </button></div>`);
        }

    })
});