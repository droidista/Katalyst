function switchToDarkMode() {
    if (document.documentElement.classList.contains("light")) {
        document.documentElement.classList.remove("light");
    }
    document.documentElement.classList.add("dark");
    window.localStorage.setItem("theme", "dark");
}

function switchToLightMode() { 
    if (document.documentElement.classList.contains("dark")) {
        document.documentElement.classList.remove("dark");
    }
    document.documentElement.classList.add("light");
    window.localStorage.setItem("theme", "light");
}

function checkTheme() {
    let theme = window.localStorage.getItem("theme");
    switch (theme) {
        case undefined: {
            break;
        }
        case "dark": {
            switchToDarkMode();
            break;
        }
        case "light": {
            switchToLightMode();
            break;
        }
    }
}

checkTheme();