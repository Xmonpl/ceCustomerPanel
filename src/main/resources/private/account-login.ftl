<!--
    @Author: Dominik 'Xmon' Dobosz
    @WebPage: https://Xmon.eu.org/
-->
<!DOCTYPE html>
<html lang="pl">
<head>
    <!-- Start Meta Tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CodeEira.eu.org - Strona Główna</title>
    <!-- End Meta Tags -->

    <!-- Start style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://cdnjs.cloudflare.com">
    <link rel="preconnect" href="https://cdn.jsdelivr.net/">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css" integrity="sha512-IgmDkwzs96t4SrChW29No3NXBIBv8baW490zk5aXvhCD8vuZM3yUSkbyTBcXohkySecyzIrUwiF/qV0cuPcL3Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@creativebulma/bulma-tooltip@1.2.0/dist/bulma-tooltip.min.css" integrity="sha256-OdzWB7wl1AMkFaqhYBnoQJGUJMDAexXa44rXs/d2n4A=" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css" integrity="sha512-1cK78a1o+ht2JcaW6g8OXYwqpev9+6GqOkz9xmBN9iUUhIndKtxwILGWYOSibOKjLsEdjyjZvYDq/cZwNeak0w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/css/style.css">
    <!-- End Style -->
</head>
<body>
<div class="pageloader is-active" id="pageloader"><span class="title">customer.CodeEira.eu.org</span></div>
<nav class="navbar is-transparent has-shadow">
    <div class="navbar-brand">
        <a class="navbar-item" href="https://bulma.io">
            <strong>CodeEira.eu.org</strong>
        </a>
        <div class="navbar-burger" data-target="navbarExampleTransparentExample">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>

    <div id="navbarExampleTransparentExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="https://bulma.io/">
                Strona Głowna
            </a>
            <a class="navbar-item" href="https://bulma.io/">
                Usługi
            </a>
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link" href="#">
                    Dokumentacja
                </a>
                <div class="navbar-dropdown is-boxed">
                    <a class="navbar-item" href="#">
                        Anty Proxy
                    </a>
                    <a class="navbar-item" href="#">
                        Sklep Minecraft
                    </a>
                    <a class="navbar-item" href="#">
                        Wykrywacz przekleństw
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="section is-large">
    <div class="container">
        <div class="box">
            <div class="columns">
                <div class="column mr-6" data-aos="fade-left">
                    <img src="/image/authentication.svg" alt="Header Image">
                </div>
                <div class="column ml-6" data-aos="fade-right">
                    <form id="login-form">
                        <h1 class="title">customer.CodeEira.eu.org - Login</h1>
                        <div id="notification"></div>
                        <div class="field mt-6">
                            <p class="control has-icons-left has-icons-right">
                                <input class="input" type="email" placeholder="E-mail" id="email" required>
                                <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control has-icons-left">
                                <input class="input" type="password" placeholder="Hasło" id="password" required>
                                <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
                            </p>
                        </div>
                        <label class="checkbox mb-3">
                            <input type="checkbox">
                            Zapamiętaj dane do logowania
                        </label>
                        <div class="field">
                            <p class="control">
                                <button class="button is-success">
                                    Zaloguj się!
                                </button>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<footer class="footer">
    <div class="container">
        <div class="columns">
            <div class="column">
                <p>
                    <strong>CodeEira.eu.org</strong>,<br>
                    <a href="https://Xmon.eu.org" target="_blank">Dominik Dobosz</a>,<br>
                    <span class="has-tooltip-arrow has-tooltip-multiline" data-tooltip="Działalność nierejestrowa (Dz. U. 2018 poz. 646).">Działalność nieewidencjonowana,</span><br>
                    Trzebinia, Polska.<br>
                    E-mail: <a href="mailto:root@codeeira.eu.org">root@CodeEira.eu.org</a><br>
                    Telefon: <a href="tel:+48785531448">+48 785 531 448</a>
                </p>
            </div>
            <div class="column">
                <div class="columns">
                    <div class="column">
                        <h2 class="subtitle">Usługi</h2>
                        <ul>
                            <li><a href="#">Logowanie</a></li>
                            <li><a href="#">Rejestracja</a></li>
                            <li><a href="#">Panel Administracyjny</a></li>
                        </ul>
                    </div>
                    <div class="column">
                        <h2 class="subtitle">CodeEira.eu.org</h2>
                        <ul>
                            <li><a href="#">Kontakt</a></li>
                            <li><a href="#">Polityka prywatności</a></li>
                            <li><a href="#">Regulaminy</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Start JavaScript Section -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js" integrity="sha512-A7AYk1fGKX6S2SsHywmPkrnzTZHrgiVT7GcQkLGDe2ev0aWb8zejytzS8wjo7PGEXKqJOrjQ4oORtnimIRZBtw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script defer>AOS.init();</script>
<script defer>
    $(document).ready(function() {
        $(".delete").click(function() {
            $('#notification').empty();
        });
        $(".navbar-burger").click(function() {
            $(".navbar-burger").toggleClass("is-active");
            $(".navbar-menu").toggleClass("is-active");
        });
        setTimeout(function() {
            $("#pageloader").removeClass('is-active');
        }, 2500);
    });

    const thisForm = document.getElementById('login-form');
    $( "#login-form" ).submit(async function (e) {
        e.preventDefault();
        $('#notification').empty();
        $.ajax({
            method:"POST",
            url: "http://localhost/account/login",
            data: {"email": document.getElementById("email").value, "password": document.getElementById("password").value}
        }).done(function(response){
            var returnedData = JSON.parse(response);
            if (returnedData['status'] === 'OK') {
                alert("zalogowano");
            } else {
                $('#notification').empty();
                $('#notification').append("<article class='message is-danger'> <div class='message-header'> <p>Bład logowania</p> <button class='delete' aria-label='delete'></button> </div> <div class='message-body'><strong>" + returnedData['message'] + "</strong></div></article>");
            }
        });
    });
</script>
<!-- End JavaScript Section -->
</body>
</html>