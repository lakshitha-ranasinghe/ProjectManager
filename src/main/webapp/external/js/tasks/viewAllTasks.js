/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var objCompleted = $('.complete');
    var objReleased = $('.release');
    var objDelete = $('.delete');
    var objInfo = $('.info');
    var assignedDate = $('#assignedDate');
    var resolvedDate = $('#resolvedDate');
    var releasedDate = $('#releasedDate');
    
    objInfo.click(function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "info",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {
                var taskValues = data.split("@");
                assignedDate.text(taskValues[0]);
                resolvedDate.text(taskValues[1]);
                releasedDate.text(taskValues[2]);
            }
        });

        $("#myModal").modal();
    });

    objCompleted.click(function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "complete",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-warning');
                } else {
                    alert('Error');
                }
            }
        });
    });
    
    objReleased.click(function(){
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "release",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-info');
                } else {
                    alert('Error');
                }
            }
        });
    });
    
    objDelete.click(function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "delete",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {
                if (data === "success") {
                    $('#tr'+id).empty();
                } else {
                    alert('Error');
                }
            }
        });
    });
    
    function replaceButtonClass(id, replaceWith) {
        var button = $('#manage' + id);
        if (button.hasClass('btn-danger')) {
            button.removeClass('btn-danger');
        }
        if (button.hasClass('btn-warning')) {
            button.removeClass('btn-warning');
        }
        if (button.hasClass('btn-info')) {
            button.removeClass('btn-info');
        }
        if (button.hasClass('btn-success')) {
            button.removeClass('btn-success');
        }
        if (button.hasClass('btn-default')) {
            button.removeClass('btn-default');
        }

        button.addClass(replaceWith);
    }
});