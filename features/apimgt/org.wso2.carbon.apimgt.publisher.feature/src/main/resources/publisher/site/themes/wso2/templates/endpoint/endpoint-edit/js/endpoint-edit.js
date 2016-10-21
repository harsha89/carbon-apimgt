var jagg = jagg || {};

$(document).ready(function () {
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded; charset=utf-8"
    });

    var endpoint_config;
    if ($('#epConfig').val() != "") {
        endpoint_config = jQuery.parseJSON($('#epConfig').val());
    }

    $("#endpoint-ui").apimEndpointUi({
        config: endpoint_config
    });

    var endpoint = $("#endpoint-name").val("");

    $.validator.addMethod('validateSpecialChars', function (value, element) {
        return !/(["\'])/g.test(value);
    }, i18n.t('The Name contains one or more illegal characters') + '( &nbsp;&nbsp; " &nbsp;&nbsp; \' &nbsp;&nbsp; )');

    $("#endpointForm").validate({
        submitHandler: function (form) {
            if(!$("#endpoint-ui").data("plugin_apimEndpointUi").validate()){
                return;
            }
            endpointAdd();
        }
    });

    var endpointAdd = function () {
        var epName = $("#epName").val();
        var epVersion = $("#epVersion").val();
        var epConfig = $('#epConfig').val(JSON.stringify($("#endpoint-ui").data("plugin_apimEndpointUi").get_endpoint_config()));
        var epRoles = $("#epRoles").val();
        var epDescription = $("#epDescriptionText").val();
        $("#epDescription").val(epDescription);
        var epSecurity = $("#epSecurity").val();
        var epAuthType = $("#epAuthType").val();
        var epUsername = $("#epUsername").val();
        var epPassword = $("#epPassword").val();
        var action = $("#action").val();
        var successMesssage;
        var failureMesssage;

        if(action == "addEndpoint") {
            successMesssage = "Endpoint Successfully Added";
            failureMesssage = "Failed to add endpoint";
        } else {
            successMesssage = "Endpoint Successfully Updated";
            failureMesssage = "Failed to update the endpoint"
        }

        $.ajax({
            type: "POST",
            url: jagg.site.context + "/site/blocks/endpoint/endpoint-edit/ajax/endpoint-edit.jag",
            data: $("#endpointForm").serialize(), // serializes the form's elements.
            success: function(data)
            {
                jagg.message({
                    content: i18n.t(successMesssage),
                    type: 'custom',
                });
            },
            error: function(data) {
                jagg.message({
                    content: i18n.t(failureMesssage),
                    type: 'confirm'
                });
            }
        });
    };
});

function showHideEndpointDivs() {
    var isSecured = $("#epSecurity").val();
    if (isSecured == "secured") {
        $("#endpointAuthType").show();
        $("#credentials").show();
    } else {
        $("#endpointAuthType").hide();
        $("#credentials").hide();
    }
};
