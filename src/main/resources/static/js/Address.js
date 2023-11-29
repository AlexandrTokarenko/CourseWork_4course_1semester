var selectedRegion = "null";
var selectedLocality = "null";
var selectedDistrict = "null";
function updateLocalities() {
    var regionSelect = document.getElementById("region");
    selectedRegion = regionSelect.options[regionSelect.selectedIndex].value;
    var localitySelect = document.getElementById("localityId");
    var localities = localitySelect.getElementsByTagName("option");
    var localityFound = false;

    for (var i = 0; i < localities.length; i++) {
        var localityRegion = localities[i].getAttribute("data-region");
        if (localityRegion === selectedRegion || localityRegion === 'Не вибрано') {
            localities[i].style.display = "block";
            localityFound = true;
        } else {
            localities[i].style.display = "none";
        }
    }

    if (!localityFound) {
        document.getElementById("localityDiv").style.display = "none";
        document.getElementById("districtDiv").style.display = "none";
          document.getElementById("localityId").value = "0"; // Set default value for locality
          document.getElementById("districtId").value = "0"; // Set default value for district
    } else {
        localitySelect.value = "0";
        document.getElementById("localityDiv").style.display = "block";
        document.getElementById("districtDiv").style.display = "none";
    }
}

function updateDistricts() {
    var localitySelect = document.getElementById("localityId");
    selectedLocality = localitySelect.options[localitySelect.selectedIndex].value;
    var districtSelect = document.getElementById("districtId");
    var districts = districtSelect.getElementsByTagName("option");
    var districtFound = false;

    for (var i = 0; i < districts.length; i++) {
        var district = districts[i].getAttribute("data-locality");
        if (district === selectedLocality) {
            districts[i].style.display = "block";
            districtFound = true;
        } else {
            districts[i].style.display = "none";
        }
    }

    if (!districtFound) {
        document.getElementById("districtDiv").style.display = "none";
        document.getElementById("districtId").value = "0"; // Set default value for district
    } else {
        districtSelect.value = "0";
        document.getElementById("districtDiv").style.display = "block";
    }
}

