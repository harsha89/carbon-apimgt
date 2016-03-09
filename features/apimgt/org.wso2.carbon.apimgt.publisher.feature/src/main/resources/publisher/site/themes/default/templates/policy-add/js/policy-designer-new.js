var index = 0;

var apiPolicy =
{
    "policyName": "",
    "policyLevel": "",
    "policyDescription": "",
    "userLevel": "",
    "executionFlows": [{}],
    "defaultQuotaPolicy": {
        "type": "",
        "limit": {
            "requestCount": 0,
            "timeUnit": "",
            "DataAmount": 0,
            "UnitTime": 0,
            "DataUnit": ""
        }
    }
};

var executionFlow = {
        "id": 0,
        "quotaPolicy": {
            "type": "",
            "limit": {
                "requestCount": 0,
                "timeUnit": "",
                "DataAmount": 0,
                "UnitTime": 0,
                "DataUnit": ""
            }
        },
        "conditions": [
            {
                "type": "IP",
                "ipType": "",
                "startingIP": "",
                "endingIP": "",
                "specificIP": "",
                "invertCondition": false,
                "enabled": false
            },
            {
                "type": "Header",
                "header": "",
                "value": "",
                "invertCondition": false,
                "enabled": false
            },
            {
                "type": "Date",
                "dateType": "",
                "startingDate": "",
                "endingDate": "",
                "specificDate": "",
                "invertCondition": false,
                "enabled": false
            },
            {
                "type": "QueryParams",
                "key": "",
                "value": "",
                "invertCondition": false,
                "enabled": false
            },
            {
                "type": "JWTClaims",
                "key": "",
                "value": "",
                "invertCondition": false,
                "enabled": false
            },
            {
                "type": "HTTPVerb",
                "httpVerb": "",
                "invertCondition": false,
                "enabled": false
            }
        ]
};

var addPolicy = function () {
    var source = $("#designer-policy-template").html();
    var executionFlow = this.executionFlow;
    executionFlow.id = index;
    Handlebars.partials['designer-policy-template'] = Handlebars.compile(source);
    var context = {
        "executionFlow": executionFlow
    };
    var output = Handlebars.partials['designer-policy-template'](context);
    $('#pipeline-content').append(output);
    index++;
};
