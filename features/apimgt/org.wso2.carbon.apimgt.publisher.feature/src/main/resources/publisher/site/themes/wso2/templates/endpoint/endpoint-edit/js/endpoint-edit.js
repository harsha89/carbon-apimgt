$(document).ready(function () {
    $.ajaxSetup({
      contentType: "application/x-www-form-urlencoded; charset=utf-8"
    });

    var endpoint_config;
    if($('#epConfig').val() != ""){
        endpoint_config = jQuery.parseJSON($('#epConfig').val());
    }
    $("#endpoint-ui").apimEndpointUi({
        config : endpoint_config
    });

    var application = $("#application-name").val("");

     $.validator.addMethod('validateSpecialChars', function(value, element) {
        return !/(["\'])/g.test(value);
     }, i18n.t('The Name contains one or more illegal characters') + '( &nbsp;&nbsp; " &nbsp;&nbsp; \' &nbsp;&nbsp; )');

    $("#appAddForm").validate({
        submitHandler: function(form) {
            applicationAdd();
        }
    });
    var applicationAdd = function(){
        var application = $("#application-name").val();
        var tier = $("#appTier").val();
        var callbackUrl = $("#callback-url").val();
        var apiPath = $("#apiPath").val();
        var goBack = $("#goBack").val();
        var description = $("#description").val();
        var status='';
        jagg.post("/site/blocks/application/application-add/ajax/application-add.jag", {
            action:"addApplication",
            application:application,
            tier:tier,
            callbackUrl:callbackUrl,
            description:description
        }, function (result) {
            if (result.error == false) {
                status=result.status;
                var date = new Date();
                date.setTime(date.getTime() + (3 * 1000));
                $.cookie('highlight','true',{ expires: date});
                $.cookie('lastAppName',application,{ expires: date});
                $.cookie('lastAppStatus',status,{ expires: date});
                if(goBack == "yes"){
                    jagg.message({content:i18n.t('Return back to API detail page?'),type:'confirm',okCallback:function(){
                    window.location.href = apiViewUrl + "?" +  apiPath;
                    },cancelCallback:function(){
                        window.location = jagg.url("/site/pages/application.jag?name=" + application );
                    }});
                } else{
                    window.location =  jagg.url("/site/pages/application.jag?name=" + application );
                }

            } else {
                jagg.message({content:result.message,type:"error"});
            }
        }, "json");
    };

    var onSubmit = function() {
        if(!$("#endpoint-ui").data("plugin_apimEndpointUi").validate()){
            return;
        }
        $('#epConfig').val(JSON.stringify($("#endpoint-ui").data("plugin_apimEndpointUi").get_endpoint_config()));
    }
});

function showHideEndpointDivs() {
    var isSecured = $("#endpointType").val();
    if (isSecured == "secured") {
        $("#endpointAuthType").show();
        $("#credentials").show();
    } else {
        $("#endpointAuthType").hide();
        $("#credentials").hide();
    }
};
