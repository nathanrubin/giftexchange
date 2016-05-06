$(document).ready(function() {
    var container = document.getElementById('mynetwork');

    var names = ["Luke", "Vader", "Han", "C-3PO", "R2-D2", "Yoda"];
    findMatchForNames(names);

    $(".addPerson").click(function () {
        var newNameObj = $("#newName");
        addPerson(newNameObj.val(), '');
        newNameObj.val('');
    });

    $("#tablePeople").on('click', '.removePerson', function () {
        $(this).parent().parent().remove();
    });

    $(".reMatch").click(function () {
        var names = [];
        $(".name").map(function () {
            names.push($(this).html());
        });
        findMatchForNames(names);
    });

    function findMatchForNames(names) {
        $.ajax({
            url: "/giftexchange/names/match",
            data: {names: names},
            traditional: true,
            success: function (mapping) {
                var mapIdToName = {};
                var data = {
                    nodes: mapping.people,
                    edges: mapping.connections
                };

                $.each(data.nodes, function (key, node) {
                    node.shape = 'ellipse';
                    node.color = {
                        border: '#555',
                        background: '#ddd',
                        highlight: {border: '#f0a30a', background: '#fccb76'}
                    };
                    mapIdToName[node.id] = node.label;
                });

                var options = {};
                 var network = new vis.Network(container, data, options);
                 network.on("select", function (params) {
                    console.log('select Event:', params);
                 });

                $("#tbodyPeople").empty();
                $.each(data.edges, function (key, edge) {
                    var fromName = mapIdToName[edge.from];
                    var toName = mapIdToName[edge.to];
                    addPerson(fromName, toName);
                });
            }
        });
    }

    function addPerson(from, to){
        $("#tbodyPeople").append('<tr><td class="name">' + from + '</td><td>' + to + '</td><td><i class="removePerson fa fa-times" aria-hidden="true"></i></td>');
    }
});