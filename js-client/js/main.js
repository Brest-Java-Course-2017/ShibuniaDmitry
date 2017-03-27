var TEAMS_URL = "http://localhost:8088/teams";
var COUNT_PLAYERS_URL = "http://localhost:8088/team/";


$.dto = null;

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
    row.append($("<td>" + '<a href="#" data-id="' + team.id + '">' + team.name + '</a></td>'));
    row.append($("<td>" + countPlayers(team.id) + "</td>"));
    row.append($("<td>" + '<a href="#" data-id="' + team.id + '"> delete</a></td>'));
}


function countPlayers(id) {
    var returnValue;
  $.ajax({
    url: COUNT_PLAYERS_URL + id + "/count",
    type: 'GET',
    dataType: 'json',
    success: function (data){
    returnValue = data}
  });
  return returnValue;
}



