$(document).ready(function () {
    var objAllBugs = $('#allBugs');
    var objAllTasks = $('#allTasks');
    var objRelease = $('#releaseButton');
    var objModalClose = $('#modalClose');
    var bugList = new Array();
    var taskList = new Array();
    var bugFound = false;
    var taskFound = false;

    objAllBugs.change(function () {
        if ($(this).is(":checked")) {
            $('.styledBug').not(this).prop('checked', this.checked);
        }
        else {
            $('.styledBug').not(this).prop('checked', false);
        }
    });

    objAllTasks.change(function () {
        if ($(this).is(":checked")) {
            $('.styledTask').not(this).prop('checked', this.checked);
        }
        else {
            $('.styledTask').not(this).prop('checked', false);
        }
    });

    objRelease.click(function () {

        $('.styledBug').each(function () {
            if ($(this).is(":checked")) {
                bugList.push($(this).attr('id'));
                bugFound = true;
            }
        });

        $('.styledTask').each(function () {
            if ($(this).is(":checked")) {
                taskList.push($(this).attr('id'));
                taskFound = true;
            }
        });
        
        if(taskFound === false){
            taskList[0] = 0;
        }
        if(bugFound === false){
            bugList[0] = 0;
        }
        
        $.ajax({
            type: "GET",
            url: "processLiveRelease",
            contentType: "application/json",
            data: {
                "bugList[]": bugList,
                "taskList[]": taskList
            },
            success: function (data) {
                var values = data.split("@"); 
                var bugFixes = values[0];
                var taskReleases = values[1];              
                $('#modalContent').html("<span class='text-success'>Release Successful!</span> <br>     " +bugFixes+" bug(s) fixed <br>     "+
                        taskReleases + " task(s) released to Live");
                $("#myModal").modal();
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });

    });
    
    objModalClose.click(function(){
       location.reload(); 
    });
});