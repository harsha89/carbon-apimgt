$(document).ready(function() {
    $("#endpoint-actions").each(function(){
        var source   = $("#endpoint-actions").html();
        var endpoint_actions = Handlebars.compile(source);

        var source   = $("#endpoint-name").html();
        var endpoint_name = Handlebars.compile(source);

        var endpoint_list = $('#endpoint-table').datatables_extended({
            serverSide: true,
            processing: true,
            paging: true,
            "ajax": {
                "url": jagg.url("/site/blocks/endpoint/endpoint-list/ajax/endpoint-list.jag?action=getEndpointsWithPagination"),
                "dataSrc": function ( json ) {
                    if(json.endpoints.length > 0){
                        $('#endpoint-table-wrap').removeClass("hide");
                    }
                    else{
                        $('#endpoint-table-nodata').removeClass("hide");
                    }
                    return json.endpoints
                }
            },
            "columns": [
                { "data": "name",
                    "render": function(data, type, rec, meta){
                        return  endpoint_name(context);
                    }
                },
                { "data": "version" },
                { "data": "type" }
            ],
        });

        $('#endpoint-table').on( 'click', 'a.deleteEndpoint', function () {
            var appName = $(this).attr("data-id");
            var apiCount = $(this).attr("data-count");
            $('#messageModal').html($('#confirmation-data').html());
            if(apiCount > 0){
                $('#messageModal h3.modal-title').html(i18n.t("Confirm Delete"));
                $('#messageModal div.modal-body').text('\n\n' +i18n.t("This endpoint is subscribed to ")
                    + apiCount + i18n.t(" APIs. ") +i18n.t("Are you sure you want to remove the endpoint ")+'"' + appName + '"'+i18n.t("? This will cancel all the existing subscriptions and keys associated with the endpoint. "));
            } else {
                $('#messageModal h3.modal-title').html(i18n.t("Confirm Delete"));
                $('#messageModal div.modal-body').text('\n\n'+i18n.t("Are you sure you want to remove the endpoint ")+'"' + appName + '" ?');
            }
            $('#messageModal a.btn-primary').html(i18n.t("Yes"));
            $('#messageModal a.btn-other').html(i18n.t("No"));
            $('#messageModal a.btn-primary').click(function() {
                jagg.post("/site/blocks/endpoint/endpoint-remove/ajax/endpoint-remove.jag", {
                    action:"removeApplication",
                    endpoint:appName
                }, function (result) {
                    if (!result.error) {
                        window.location.reload(true);
                    } else {
                        jagg.message({content:result.message,type:"error"});
                    }
                }, "json");
            });
            $('#messageModal a.btn-other').click(function() {
                window.location.reload(true);
            });
            $('#messageModal').modal();


        });
    });

    $(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
        $(".curl_command").each(function(){ $(this).data("plugin_codeHighlight").editor.refresh()});
    });

});




