/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var objStickIt = $('#stickIt');
    var objNotes = $('#notes');
    var objNote = $('.specialList');

    objStickIt.click(function () {
        var description = $('#description');
        $.ajax({
            type: "GET",
            url: "stick",
            contentType: "application/json",
            data: {
                "description": description.val()
            },
            success: function (data) {
                if (data === "success") {
                    objNotes.append(
                            '<li class="specialList">' +
                            '<a class="specialAnchor" href="#">' +
                            '<p class="specialPara">' + description.val() + '</p>' +
                            '</a>' +
                            '</li>  ');

                    description.val(null);
                }
            },
            error: function (data) {
                alert('error');
            }
        });
    });

    objNote.dblclick(function () {
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
                    $('#'+id).empty();
                } else {
                    alert('Error');
                }
            }
        });
    });
});