function deleteApp(linkObj) {
    jagg.sessionAwareJS({redirect:'site/pages/applications.jag'});
    var theTr = $(linkObj).parent().parent();
    var appName = $(theTr).attr('data-value');
    var apiCount = $(theTr).attr('api-count');
    $('#messageModal').html($('#confirmation-data').html());
    if(apiCount > 0){
        $('#messageModal h3.modal-title').html(i18n.t("Confirm Delete"));
        $('#messageModal div.modal-body').text('\n\n' +i18n.t("This application is subscribed to ")
            + apiCount + i18n.t(" APIs. ") +i18n.t("Confirm Delete")+'"' + appName + '"'+i18n.t("? This will cancel all the existing subscriptions and keys associated with the application. "));
    } else {
        $('#messageModal h3.modal-title').html(i18n.t("Confirm Delete"));
        $('#messageModal div.modal-body').text('\n\n'+i18n.t("Are you sure you want to remove the application ")+'"' + appName + '" ?');
    }
    $('#messageModal a.btn-primary').html(i18n.t("Yes"));
    $('#messageModal a.btn-other').html(i18n.t("No"));
    $('#messageModal a.btn-primary').click(function() {
        jagg.post("/site/blocks/application/application-remove/ajax/application-remove.jag", {
            action:"removeApplication",
            application:appName
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

}

function hideMsg() {
    $('#applicationTable tr:last').css("background-color", "");
    $('#appAddMessage').hide("fast");
}

$(document).ready(function() {
    if ($.cookie('highlight') != null && $.cookie('highlight') == "true") {
        $.cookie('highlight', "false");

        $('#applicationTable tr:last').css("background-color", "#d1dce3");
        if($.cookie('lastAppStatus')=='CREATED'){
        $('#appAddPendingMessage').show();
        $('#applicationPendingShowName').text($.cookie('lastAppName'));
        var t = setTimeout("hideMsg()", 3000);
        }else{
        $('#appAddMessage').show();
        $('#applicationShowName').text($.cookie('lastAppName'));
        var t = setTimeout("hideMsg()", 3000);

        }
    }
});
