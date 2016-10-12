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
        var epDescription = $("#epDescription").val();
        var epSecurity = $("#epSecurity").val();
        var epAuthType = $("#epAuthType").val();
        var epUsername = $("#epUsername").val();
        var epPassword = $("#epPassword").val();

        $.ajax({
            type: "POST",
            url: jagg.site.context + "/site/blocks/endpoint/endpoint-edit/ajax/endpoint-edit.jag",
            data: $("#endpointForm").serialize(), // serializes the form's elements.
            success: function(data)
            {
                alert(data); // show response from the php script.
            }
        });

       /* jagg.post("/site/blocks/endpoint/endpoint-edit/ajax/endpoint-edit.jag", {
            action: "addEndpoint",
            epName: epName,
            epVersion: epVersion,
            epConfig: epConfig,
            epRoles: epRoles,
            epDescription: epDescription,
            epSecurity: epSecurity,
            epAuthType: epAuthType,
            epUsername: epUsername,
            epPassword: epPassword
        }, function (result) {
            window.location =  jagg.url("/site/pages/endpoints.jag" );
        }, "json");*/
    };
});

function showHideEndpointDivs() {
    var isSecured = $("#endpointType").val();
    if (isSecured == "secured") {
        $("#endpointAuthType").show();
        $("#credentials").show();
        $("#epSecurity").val("secured");
    } else {
        $("#endpointAuthType").hide();
        $("#credentials").hide();
        $("#epSecurity").val("nonecured");
    }
};
