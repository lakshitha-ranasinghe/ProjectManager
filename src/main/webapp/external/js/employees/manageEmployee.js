/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    var objDeleteButton = $('.deleteButton');
    var objEmployeeTable = $('#employeeTable');
    
    objDeleteButton.click(function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "delete",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {
                $('#tr'+id).remove();
            }
        });
    });
});
