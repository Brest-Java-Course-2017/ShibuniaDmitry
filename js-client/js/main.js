var TEAMS_URL = "http://localhost:8088/teams";
var TEAM_CHANGE_URL = "http://localhost:8088/team";

$.dto = null;

$('#btnSave').click(function () {
    if ($('#id').val() == '')
        addTeam();
    else
        updateTeam(id);
    invisibleInput();

    return false;
});


$('#btnNew').click(function () {
        visibleInput();
});


$('#teamList').on("click", ".small-button.delete", function () {
        var id = $(this).parent().find(".teamDel").attr("teamId");
        invisibleInput();
        deleteTeam(id);
});

$('#teamList').on("click", ".small-button.edit", function () {
        var selectedTeamId = $(this).parent().find(".teamDel").attr("teamId");
        visibleInput();
         $.each(dto, function (index) {
                    if (dto[index].id == selectedTeamId) {
                        $("#id").val(selectedTeamId);
                        $("#name").val(dto[index].name);
        }
         });

});

$(document).on("click", "a", function() {

    var selectedTeamId = $(this).data("id");
    var uri = 'players.html?teamId=' + selectedTeamId;
    window.location = uri;
});

invisibleInput();
findAll();

function findAll() {
        console.log('findAll');
        $.ajax({
            type: 'GET',
            url: TEAMS_URL,
            dataType: "json", // data type of response
            success: renderList,
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('findAll: ' + textStatus);
            }
        });
}

function renderList(data) {
        dto = data == null ? [] : (data instanceof Array ? data : [data]);
        $('#teamList tr').remove();
        $.each(dto, function (index, team) {
            drawRow(team);
        });
}

function drawRow(team) {
        var row = $("<tr />");

        $("#teamList").append(row);
        row.append($('<td>' + '<a href="#" data-id="' + team.id + '">' + team.name + '</a></td>'));
        row.append($("<td>" + team.playersQuantity + "</td>"));
        row.append($('<td>'
            + '<span class="small-button delete"><button>delete</button></span>' + '<span class="teamDel" teamId="' + team.id + '">'
            + '<span class="small-button edit"><button>edit</button></span>' + '<span class="teamDel" teamId="' + team.id + '">'));
}

function deleteTeam(teamId) {
        console.log('deleteTeam');
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: TEAM_CHANGE_URL +  '/' + teamId,
            success: function (data, textStatus, jqXHR) {
                alert('Team deleted successfully');
                findAll();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('deleteTeam error: ' + errorThrown);
            }
        });
}

function addTeam() {
        console.log('addTeam');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: TEAM_CHANGE_URL,
            dataType: "json",
            data: formToJSON(),
            success: function (data, textStatus, jqXHR) {
                alert('Team created successfully');
                console.log('Team created successfully');
                findAll();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addTeam error: ' + errorThrown);
            }
        });
}

function updateTeam() {
        console.log('updateTeam');
        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: TEAM_CHANGE_URL,
            data: formToJSON(),
            success: function (data, textStatus, jqXHR) {
                alert('Team updated successfully');
                console.log('Team updated successfully');
                findAll();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('updateTeam error: ' + errorThrown);
            }
        });
}

function formToJSON() {
        var id = $('#id').val();
        return JSON.stringify({
            "id": $('#id').val() == "" ? null : parseInt(id),
            "name": $('#name').val()
        });
}

function invisibleInput(){
    var div = document.getElementById('teamForm');
    div.style.visibility = 'hidden';
}

function visibleInput(){
        var div = document.getElementById('teamForm');
        $("#id").val("")
        $("#name").val("")
        div.style.visibility = 'visible';
}