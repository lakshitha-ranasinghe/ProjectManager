/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {

    var objInfo = $('.info');
    var objResolved = $('.resolve');
    var objReleased = $('.release');
    var objClosed = $('.closeIt');
    var objInvalid = $('.invalid');
    var objDelete = $('.delete');
    var objAssign = $('.assign');
    var objReportedBy = $('#reportedBy');
    var objAssignedDate = $('#assignedDate');
    var objClosedDate = $('#closedDate');
    var objReleasedDate = $('#releasedDate');
    var objResolvedDate = $('#resolvedDate');
    var objImage = $('#imageRow');
    var objImageClose = $('#imageClose');
    var enlargedImage;
    var enlargedImageModal = $('#myImageModal');

    objInfo.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "info",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                var bugValues = data.split("@");
                objReportedBy.text(bugValues[0]);
                objAssignedDate.text(bugValues[1]);
                objResolvedDate.text(bugValues[2]);
                objReleasedDate.text(bugValues[3]);
                objClosedDate.text(bugValues[4]);
                var imagePath = bugValues[5];
                if (imagePath !== "NoImage") {
                    $('#imageRow').html(null);
                    enlargedImage = imagePath;
                    $('#imageRow').html('<div class="blog-img"><img id="image" src="' + imagePath + '" alt="..."/></div>');
                }
                else{
                    $('#imageRow').html(null);
                }
            }
        });

        $("#myModal").modal();
    });

    var imagemodal = document.getElementById('myImageModal');
    var modalImg = document.getElementById("img01");

    objImage.click(function() {
        imagemodal.style.display = "block";
        modalImg.src = enlargedImage;
    });

    objImageClose.click(function() {
        imagemodal.style.display = "none";
    });

    objResolved.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "resolve",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-warning');
                    $('#status'+id).html("Resolved");
                } else {
                    alert('Error');
                }
            }
        });
    });

    objReleased.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "release",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-info');
                    $('#status'+id).html("Released");
                } else {
                    alert('Error');
                }
            }
        });
    });

    objInvalid.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "invalid",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-default');
                    $('#status'+id).html("Invalid");
                } else {
                    alert('Error');
                }
            }
        });
    });

    objClosed.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "close",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-success');
                    $('#status'+id).html("Closed");
                } else {
                    alert('Error');
                }
            }
        });
    });

    objDelete.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "delete",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                if (data === "success") {
                    $('#tr' + id).empty();
                } else {
                    alert('Error');
                }
            }
        });
    });
    
    objAssign.click(function() {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "assign",
            contentType: "application/json",
            data: {
                "id": id
            },
            success: function(data) {
                if (data === "success") {
                    replaceButtonClass(id, 'btn-danger');
                    $('#status'+id).html("Assigned");
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