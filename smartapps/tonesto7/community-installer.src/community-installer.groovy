/*
*   Universal Communtity App Installer
*   Copyright 2018 Anthony Santilli, Corey Lista
*
// /**********************************************************************************************************************************************/
import groovy.json.*
import java.text.SimpleDateFormat

definition(
    name			: "Community-Installer",
    namespace		: "tonesto7",
    author			: "tonesto7",
    description		: "The Community Devices/SmartApp Installer",
    category		: "My Apps",
    singleInstance	: true,
    iconUrl			: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url		: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX3Url		: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png")
/**********************************************************************************************************************************************/
private releaseVer() { return "5.0.0109" }
private appVerDate() { "1-09-2018" }
/**********************************************************************************************************************************************/
preferences {
    page name: "startPage"
    page name: "mainPage"
}

mappings {
    path("/installStart") { action: [GET: "installStartHtml"] }
}

def startPage() {
    if(!atomicState?.accessToken) { getAccessToken() }
	if(!atomicState?.accessToken) {
		return dynamicPage(name: "startPage", title: "Status Page", nextPage: "", install: false, uninstall: true) {
			section ("Status Page:") {
				def title = ""
                def desc = ""
				if(!atomicState?.accessToken) { title="OAUTH Error"; desc = "OAuth is not Enabled for ${app?.label} application.  Please click remove and review the installation directions again"; }
				else { title="Unknown Error"; desc = "Application Status has not received any messages to display";	}
				log.warn "Status Message: $desc"
				paragraph title: "$title", "$desc", required: true, state: null
			}
		}
	}
    else { return mainPage() }
}

def mainPage() {
    dynamicPage (name: "mainPage", title: "", install: true, uninstall: true) {
        def theURL = "https://consigliere-regional.api.smartthings.com/?redirect=${getAppEndpointUrl("installStart")}"
        log.trace getAppEndpointUrl("installStart")
        section("Automatic Setup") {
            paragraph title: "What now?", "Tap on the input below to launch the Installer Web App"
            href "", title: "Get Started", url: theURL, style: "embedded", required: false, description: "", image: ""
        }
    }
}

def webHeadHtml(title, verStr="") {
    return """
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="${title}">
        <meta name="author" content="Anthony S.">
        <meta http-equiv="cleartype" content="on">
        <meta name="MobileOptimized" content="320">
        <meta name="HandheldFriendly" content="True">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <title>${title}</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <script src="https://use.fontawesome.com/a81eef09c0.js" defer></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js" defer></script>
        <link href="https://echosistant.com/es5_content/css/es5_master.min.css" rel="stylesheet">
        <link href="https://echosistant.com/es5_content/css/es5_web.min.css" rel="stylesheet">
    """
}

def webFooterHtml(verStr="") {
    return """
        <footer class="page-footer center-on-small-only fixed-bottom">
            <div class="footer-copyright">
                <div class="containter-fluid">
                    Copyright © 2018 Anthony Santilli & Corey Lista
                </div>
            </div>
        </footer>
        <script type="text/javascript" src="https://echosistant.com/es5_content/js/popper.min.js" defer></script>
        <script type="text/javascript" src="https://echosistant.com/es5_content/js/bootstrap.min.js" defer></script>
        <script type="text/javascript" src="https://echosistant.com/es5_content/js/mdb.min.js" defer></script>
    """
}

def getLoaderAnimation() {
    return """
        <svg id="loader" height="100%" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid" class="lds-double-ring">
            <circle cx="50" cy="50" ng-attr-r="{{config.radius}}" ng-attr-stroke-width="{{config.width}}" ng-attr-stroke="{{config.c1}}" ng-attr-stroke-dasharray="{{config.dasharray}}" fill="none" stroke-linecap="round" r="40" stroke-width="7" stroke="#18B9FF" stroke-dasharray="62.83185307179586 62.83185307179586" transform="rotate(139.357 50 50)">
            <animateTransform attributeName="transform" type="rotate" calcMode="linear" values="0 50 50;360 50 50" keyTimes="0;1" dur="1.8s" begin="0s" repeatCount="indefinite"></animateTransform>
            </circle>
            <circle cx="50" cy="50" ng-attr-r="{{config.radius2}}" ng-attr-stroke-width="{{config.width}}" ng-attr-stroke="{{config.c2}}" ng-attr-stroke-dasharray="{{config.dasharray2}}" ng-attr-stroke-dashoffset="{{config.dashoffset2}}" fill="none" stroke-linecap="round" r="32" stroke-width="7" stroke="#FF7F27" stroke-dasharray="50.26548245743669 50.26548245743669" stroke-dashoffset="50.26548245743669" transform="rotate(-139.357 50 50)">
            <animateTransform attributeName="transform" type="rotate" calcMode="linear" values="0 50 50;-360 50 50" keyTimes="0;1" dur="1.8s" begin="0s" repeatCount="indefinite"></animateTransform>
            </circle>
            <text id="loaderText1" fill="gray" stroke-width="0" x="50%" y="50%" text-anchor="middle" class="loaderText">Please</text>
            <text id="loaderText2" fill="gray" stroke-width="0" x="50%" y="60%" text-anchor="middle" class="loaderText">Wait</text>
        </svg>
    """
}

def installStartHtml() {
    def randVerStr = "?=${now()}"
    def html = """
        <html lang="en">
        <head>
            ${webHeadHtml("ST Community Install Tool", randVerStr)}
            <script type="text/javascript">
                var functionType = "addRepo";
                var serverUrl = '${apiServerUrl('')}';
                var locId = '${location?.id}'
                // var repoData = ${new JsonOutput().toJson([owner: 'tonesto7', repoName: 'echosistant-alpha', branch: 'master', namespace: 'com.tonesto7'])};
                // var appNames = ${new JsonOutput().toJson(['ST Home Manager':'smartapps/tonesto7/st-home-manager.src/st-home-manager.groovy', 'Event Reporting App':'smartapps/tonesto7/event-reporting-app.src/event-reporting-app.groovy'])};
            </script>
            <script src="https://echosistant.com/es5_content/js/ignore_me.js" async></script>
        </head>
        <body>
            <header>
                <nav class="navbar navbar-dark sticky-top navbar-expand-lg">
                    <a class="navbar-brand" href="#"><img src="https://echosistant.com/es5_content/images/es5_logo.png" height="30" class="d-inline-block align-top" alt=""> Community Installer</a>
                </nav>
            </header>
            <main>
                <div class="container">
                    <section id="stContent">
                        <div style="width: 100%; height: 200px; text-align: center;">
                            <h2 class="h2-responsive mb-2" style="font-weight: 400;">Software Installer</h2>
                            <hr class="white">
                            <div id="loaderDiv" class="row fadeIn fadeOut">
                                <div class="col-lg-12 mb-r">
                                    ${getLoaderAnimation()}
                                </div>
                            </div>
                            <div class="row fadeIn fadeOut">
                                <div class="col-lg-12 mb-r">
                                    <div class="listDiv">
                                        <div id="resultList">
                                            <h3 id="resultsTitle" style="display: none;">Install Results</h3>
                                            <ul id="resultUl"></ul>
                                        </div>
                                    </div>
                                    <i id="finishedImg" class='fa fa-check' style="display: none;"></i>
                                    <div id="results"></div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </main>
            ${webFooterHtml(randVerStr)}
        </body>
        </html>"""
    render contentType: "text/html", data: html
}

def installed() {
    LogAction("Installed with settings: ${settings}", "debug", false)
    atomicState?.isInstalled = true
    initialize()
}

def updated() {
    log.trace ("${app?.getLabel()} | Now Running Updated() Method")
    if(!atomicState?.isInstalled) { atomicState?.isInstalled = true }
    initialize()
}

def initialize() {
    if (!atomicState?.accessToken) {
        LogAction("Access token not defined. Attempting to refresh. Ensure OAuth is enabled in the SmartThings IDE.", "error", false)
        getAccessToken()
    }
}

def uninstalled() {
	revokeAccessToken()
    log.warn("${app?.getLabel()} has been Uninstalled...")
}

def getAccessToken() {
    try {
        if(!atomicState?.accessToken) {
            log.error "SmartThings Access Token Not Found... Creating a New One!!!"
            atomicState?.accessToken = createAccessToken()
        } else { return true }
    }
    catch (ex) {
        log.error "Error: OAuth is not Enabled for ${app?.label}!.  Please click remove and Enable Oauth under the SmartApp App Settings in the IDE"
        return false
    }
}

def getAppImg(file)	    { return "https://echosistant.com/es5_content/images/$file" }
def getAppVideo(file)	{ return "https://echosistant.com/es5_content/videos/$file" }
def getAppEndpointUrl(subPath)	{ return "${apiServerUrl("/api/smartapps/installations/${app.id}${subPath ? "/${subPath}" : ""}?access_token=${atomicState.accessToken}")}" }