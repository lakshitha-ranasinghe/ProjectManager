/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var objGenerateTaskButton = $('.generateTaskReport');
    var objGenerateBugButton = $('.generateBugReport');

    objGenerateTaskButton.click(function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "processTaskReleaseReport",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    });

    objGenerateBugButton.click(function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "processBugReleaseReport",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function (data) {
                var blob = new Blob([data]);
                var link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = "BugReport.pdf";
                link.click();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    });
});