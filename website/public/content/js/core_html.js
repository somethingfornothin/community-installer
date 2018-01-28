function updateHeadHtml() {
    let html = '';
    html += '\n                 <meta name="description" content="Community Installer">';
    html += '\n                 <meta name="author" content="Anthony S.">';
    html += '\n                 <title>Community Installer</title>';
    html += '\n                 <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">';
    html += '\n                 <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"></link>';
    html += '\n                 <script src="https://use.fontawesome.com/a81eef09c0.js" defer></script>';
    html += '\n                 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous" async></script>';
    html += '\n                 <script src="https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js" async></script>';
    html += '\n                 <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>';
    html += '\n                 <style>';
    html += '\n                     input[type=checkbox]:disabled:checked+label:before { border-color: transparent rgba(75, 243, 72, 0.46) rgba(36, 204, 103, 0.46) transparent; }';
    html += '\n                 </style>';
    $('head').append(html);
}

function buildCoreHtml() {
    let html = '';
    html += '\n       <header>';
    html += '\n           <nav class="navbar fixed-top navbar-expand-lg navbar-dark ">';
    html += '\n               <a class="navbar-brand" href="' + homeUrl + '"><img src="' + baseAppUrl + '/content/images/app_logo.png" height="30" class="d-inline-block align-top" alt=""> Installer</a>';
    html += '\n               <!-- Collapse button -->';
    html += '\n               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>';

    html += '\n               <!-- Collapsible content -->';
    html += '\n               <div class="collapse navbar-collapse" id="navbarSupportedContent">';
    html += '\n                   <!-- Links -->';
    html += '\n                   <ul class="navbar-nav mr-auto">';
    html += '\n                       <li class="nav-item active">';
    html += '\n                            <a class="nav-link" href="' + homeUrl + '"><i id="homeBtn" class="fa fa-home"></i> Home <span class="sr-only">(current)</span></a>';
    html += '\n                       </li>';
    html += '\n                   </ul>';
    html += '\n                   <!-- Links -->';
    html += '\n               </div>';
    html += '\n               <!-- Collapsible content -->';
    html += '\n           </nav>';
    html += '\n       </header>';
    html += '\n       <main>';
    html += '\n           <div id="mainDiv" class="container" style="min-width: 380px; max-width: 700px; height: auto; min-height: 100%; ">';
    html += '\n               <section class="px-3">';
    html += '\n                   <div class="w-100 text-center">';
    html += '\n                       <h2 id="sectTitle" class="h2-responsive" style="font-weight: 400; display: none;">Software Installer</h2>';
    html += '\n                       <div id="loaderDiv" class="flex-row fadeIn fadeOut">';
    html += '\n                           <div class="d-flex flex-column justify-content-center align-items-center" style="height: 200px;">';
    html += '\n                               <svg id="loader" height="100%" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid" class="lds-double-ring">';
    html += '\n                                   <circle cx="50" cy="50" ng-attr-r="{{config.radius}}" ng-attr-stroke-width="{{config.width}}" ng-attr-stroke="{{config.c1}}" ng-attr-stroke-dasharray="{{config.dasharray}}" fill="none" stroke-linecap="round" r="40" stroke-width="7" stroke="#18B9FF" stroke-dasharray="62.83185307179586 62.83185307179586" transform="rotate(139.357 50 50)">';
    html += '\n                                       <animateTransform attributeName="transform" type="rotate" calcMode="linear" values="0 50 50;360 50 50" keyTimes="0;1" dur="1.8s" begin="0s" repeatCount="indefinite"></animateTransform>';
    html += '\n                                   </circle>';
    html +=
        '\n                                   <circle cx="50" cy="50" ng-attr-r="{{config.radius2}}" ng-attr-stroke-width="{{config.width}}" ng-attr-stroke="{{config.c2}}" ng-attr-stroke-dasharray="{{config.dasharray2}}" ng-attr-stroke-dashoffset="{{config.dashoffset2}}" fill="none" stroke-linecap="round" r="32" stroke-width="7" stroke="#FF7F27" stroke-dasharray="50.26548245743669 50.26548245743669" stroke-dashoffset="50.26548245743669" transform="rotate(-139.357 50 50)">';
    html += '\n                                       <animateTransform attributeName="transform" type="rotate" calcMode="linear" values="0 50 50;-360 50 50" keyTimes="0;1" dur="1.8s" begin="0s" repeatCount="indefinite"></animateTransform>';
    html += '\n                                   </circle>';
    html += '\n                                   <text id="loaderText1" fill="gray" stroke-width="0" x="50%" y="50%" text-anchor="middle" class="loaderText">Please</text>';
    html += '\n                                   <text id="loaderText2" fill="gray" stroke-width="0" x="50%" y="60%" text-anchor="middle" class="loaderText">Wait</text>';
    html += '\n                               </svg>';
    html += '\n                           </div>';
    html += '\n                       </div>';
    html += '\n                       <div id="listContDiv" class="row fadeIn fadeOut" style="display: none;"></div>';
    html += '\n                       <div id="appViewDiv" class="row fadeIn fadeOut" style="display: none;"></div>';

    html += '\n                       <div id="actResultsDiv" class="row fadeIn fadeOut mb-5" style="display: none;">';
    html += '\n                           <div class="listDiv">';
    html += '\n                               <div id="resultList">';
    html += '\n                                   <div class="card card-body card-outline" style="background-color: transparent; line-height:1.0;">';

    html += '\n                                       <div class="row">';
    html += '\n                                           <div class="d-flex w-100 flex-column mb-3">';
    html += '\n                                               <i id="finishedImg" class="fa fa-check" style="display: none;"></i>';
    html += '\n                                               <div id="results"></div>';

    html += '\n                                               <div class="d-flex flex-column justify-content-center mx-2">';
    html += '\n                                                   <div class="d-flex flex-column align-items-center" style="border: 1px solid gray; border-radius: 10px;">';

    html += '\n                                                       <div class="d-flex flex-column justify-content-center align-items-center">';
    html += '\n                                                           <h6 id="ideResultsTitle" class="mt-2 mb-0" style="display: none;"><u>IDE Authentication</u></h6>';
    html += '\n                                                           <ul id="ideResultUl" class="w-100 px-4" style="display: none;"></ul>';
    html += '\n                                                       </div>';

    html += '\n                                                       <div class="d-flex w-100 flex-column justify-content-center align-items-center">';
    html += '\n                                                           <h6 id="repoResultsTitle" class="mt-2 mb-0" style="display: none;"><u>GitHub Integration</u></h6>';
    html += '\n                                                           <ul id="repoResultUl" class="w-100 px-3" style="display: none;"></ul>';
    html += '\n                                                       </div>';

    html += '\n                                                       <div class="d-flex w-100 flex-column justify-content-center align-items-center">';
    html += '\n                                                           <h6 id="appResultsTitle" class="mt-2 mb-0" style="display: none;"><u>SmartApps</u></h6>';
    html += '\n                                                           <ul id="appResultUl" class="w-100 px-3" style="display: none;"></ul>';
    html += '\n                                                       </div>';

    html += '\n                                                       <div class="d-flex w-100 flex-column justify-content-center align-items-center">';
    html += '\n                                                           <h6 id="devResultsTitle" class="mt-2 mb-0" style="display: none;"><u>Devices</u></h6>';
    html += '\n                                                           <ul id="devResultUl" class="w-100 px-3" style="display: none;"></ul>';
    html += '\n                                                       </div>';

    html += '\n                                                   </div>';
    html += '\n                                               </div>';

    html += '\n                                               <div id="resultsDone" class="mt-4" style="display: none;"><small>Press Back/Done Now</small></div>';
    html += '\n                                          </div>';

    html += '\n                                     </div>';
    html += '\n                                 </div>';
    html += '\n                            </div>';
    html += '\n                       </div>';
    html += '\n               </section>';
    html += '\n           </div>';
    html += '\n       </main>';
    html += '\n       <footer id="ftrSect" class="page-footer center-on-small-only fixed-bottom m-0 p-0">';
    html += '\n           <div class="footer-copyright">';
    html += '\n               <div class="containter-fluid">';
    html += '\n                   <small>Copyright \u00A9 2018 Anthony Santilli & Corey Lista</small>';
    html += '\n               </div>';
    html += '\n           </div>';
    html += '\n       </footer>';
    $('body').css({ 'margin-top': '70px', 'overflow-x': 'hidden', 'overflow-y': 'auto', 'margin-bottom': '100px' });
    $('#bodyDiv').html(html);
}