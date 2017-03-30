var playerId = getParameterByName('id');
var teamId = getParameterByName('teamId')
var PLAYER_CHANGE_URL = "http://localhost:8088/team/player";

if(playerId != "null"){
    $("#id").val(playerId);
    $("#name").val(getParameterByName('name'));
    $("#surname").val(getParameterByName('surname'));
    $("#datepicker").val(getParameterByName('date'));
}

$('#saveBtn').click(function () {
    if ($('#id').val() == '')
        addPlayer();
    else
        updatePlayer();
    return false;
});

$('#btnCancel').click(function () {

    window.location = 'players.html?teamId=' + getParameterByName('backTeamId');

});

function addPlayer() {
        console.log('addPlayer');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: PLAYER_CHANGE_URL,
            dataType: "json",
            data: formToJSON(),
            success: function (data, textStatus, jqXHR) {
                alert('Player created successfully');
                console.log('Player created successfully');
                window.location = 'players.html?teamId=' + teamId;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addPlayer error: ' + errorThrown);
            }
        });
}

function updatePlayer() {
        console.log('updatePlayer');
        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: PLAYER_CHANGE_URL,
            data: formToJSON(),
            success: function (data, textStatus, jqXHR) {
                alert('Player updated successfully');
                console.log('Player updated successfully');
                window.location = 'players.html?teamId=' + teamId;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('updatePlayer error: ' + errorThrown);
            }
        });
}

function formToJSON() {
        var id = $('#id').val();
        return JSON.stringify({
            "id": $('#id').val() == "" ? null : parseInt(id),
            "teamId": parseInt(teamId),
            "name": $('#name').val(),
            "surname": $('#surname').val(),
            "acceptanceDate": $('#datepicker').val()
        });
}

function getParameterByName(name, url) {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}