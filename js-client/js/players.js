var teamId = location.search.split('teamId=')[1];
var PLAYERS_URL = "http://localhost:8088/team/" + teamId + "/players";
var PLAYER_CHANGE_URL = "http://localhost:8088/team/player";

$.dto = null;

$("#from").val("");
$("#to").val("");

$('#playerList').on("click", ".small-button.delete", function () {
        var id = $(this).parent().find(".playerDel").attr("playerId");
        deletePlayer(id);
});

$('#playerList').on("click", ".small-button.edit", function () {
        var id = $(this).parent().find(".playerEdit").attr("playerId");
        var uri = 'playerAddOrEditForm.html?id=' + id;
        $.each(dto, function (index) {
                    if (dto[index].id == id) {
                        uri += '&name=' + dto[index].name;
                        uri += '&surname=' + dto[index].surname;
                        uri += '&date=' + dateToString( dto[index].acceptanceDate.dayOfMonth,
                                                        dto[index].acceptanceDate.month,
                                                        dto[index].acceptanceDate.year);
                    }
        });
        uri += '&teamId=' + teamId;
        window.location = uri;
});


$('#btnNew').click(function () {
        var playerId = null;
        var uri = 'playerAddOrEditForm.html?id=' + playerId + '&teamId=' + teamId;
        uri += '&backTeamId=' + teamId;
        window.location = uri;
});

$('#btnFilter').click(function () {
        var from = $('#from').val();
        var to =  $('#to').val();
        if(from == '')
            from = "0001-01-01";
        if(to == '')
            to = "3000-01-01";
        filterPlayers(from, to);
});

$('#btnBack').click(function () {
    window.location = 'index.html';
});

findAll();

function deletePlayer(id) {
        console.log('deletePlayer');
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: PLAYER_CHANGE_URL +  '/' + id,
            success: function (data, textStatus, jqXHR) {
                alert('Player deleted successfully');
                findAll();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('deletePlayer error: ' + errorThrown);
            }
        });
}
function findAll() {
        console.log('findAll');
        $.ajax({
            type: 'GET',
            url: PLAYERS_URL,
            dataType: "json", // data type of response
            success: renderList,
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('findAll: ' + textStatus);
            }
        });
}

function filterPlayers(from, to) {
        console.log('filterPlayers');
        $.ajax({
            type: 'GET',
            url: PLAYERS_URL + "/" + from + "/" + to,
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
        $('#playerList tr').remove();
        $.each(dto, function (index, player) {
            drawRow(player);
        });
}

function drawRow(player) {
        var row = $("<tr />");

        $("#playerList").append(row);
        row.append($("<td>" + player.name + "</td>"));
        row.append($("<td>" + player.surname + "</td>"));
        row.append($("<td>" +   dateToString(player.acceptanceDate.dayOfMonth,
                                player.acceptanceDate.month,
                                player.acceptanceDate.year) + "</td>"));
        row.append($('<td>'
            + '<span class="small-button delete"><button>delete</button></span>' + '<span class="playerDel" playerId="' + player.id + '">'
            + '<span class="small-button edit"><button>edit</button></span>' + '<span class="playerEdit" playerId="' + player.id + '">'));

}

function dateToString(day, month, year){
        var returnValue = year.toString() + "-";
        switch(month){
        case "JANUARY": returnValue += "01-"; break;
        case "FEBRUARY": returnValue += "02-"; break;
        case "MARCH": returnValue += "03-"; break;
        case "APRIL": returnValue += "04-"; break;
        case "MAY": returnValue += "05-"; break;
        case "JUNE": returnValue += "06-"; break;
        case "JULY": returnValue += "07-"; break;
        case "AUGUST": returnValue += "08-"; break;
        case "SEPTEMBER": returnValue += "09-"; break;
        case "OCTOBER": returnValue += "10-"; break;
        case "NOVEMBER": returnValue += "11-"; break;
        case "DECEMBER": returnValue += "12-"; break;
        }
        if(day < 10)
            returnValue += "0";
        returnValue += day.toString();
        return returnValue;
}
