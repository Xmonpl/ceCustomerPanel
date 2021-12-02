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
    <style>
        .user-avatar{
            margin-right: 0.75rem;
            display: inline-flex;
            width: 1.75rem;
            height: 1.75rem;
        }
        ul{
            list-style: none;
        }
        .menu-list a:hover {
            background: #aca6ff;
        }
        .menu-list a.is-active {
            background: #6c63ff;
        }
        .menu{
            background-color: #fff;
            border-radius: 6px;
            box-shadow: 0 .5em 1em -.125em rgba(10,10,10,.1),0 0 0 1px rgba(10,10,10,.02);
            color: #4a4a4a;
            display: block;
            padding: 1.25rem;
        }
    </style>
    <!-- End Style -->
</head>
<body>
<div id="modals"></div>
<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="https://bulma.io">
            <strong>customer.CodeEira.eu.org</strong>
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">

        <div class="navbar-end">
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">
                    <div class="user-avatar">
                        <img src='/api/avatar/$user.getUUID()' alt="John Doe">
                    </div>
                    <span>$user.full_name</span>
                </a>

                <div class="navbar-dropdown">
                    <a class="navbar-item">
                        About
                    </a>
                    <a class="navbar-item">
                        Jobs
                    </a>
                    <a class="navbar-item">
                        Contact
                    </a>
                    <hr class="navbar-divider">
                    <a class="navbar-item">
                        Report an issue
                    </a>
                </div>
            </div>
        </div>
        <a class="navbar-item">
            <span class="icon"><i class="fas fa-question-circle"></i></span>
        </a>
        <a class="navbar-item">
            <span class="icon"><a href="/account/dashboard/logout"><i class="fas fa-sign-out-alt"></i></a></span>
        </a>
    </div>
</nav>
<div class="columns">
    <div class="column is-one-fifth pl-4 pt-4">
        <aside class="menu">
            <div class="columns is-mobile">
                <div class="column is-half is-offset-one-quarter">
                    <img class="image is-128x128" src='/api/avatar/$user.getUUID()' alt="John Doe">
                    <p>$user.full_name</p>
                    <p>$user.email</p>
                    <span class="tag is-rounded is-success ml-3">Portfel: $user.balance</span>
                </div>
            </div>
            <ul class="menu-list">
                <li><a href="/account/dashboard">Panel użytkownika</a></li>
            </ul>
            <p class="menu-label">
                Usługi Główne
            </p>
            <ul class="menu-list">
                <li><a>Anty Proxy</a></li>
                <li><a>Wykrywacz przekleństw</a></li>
                <li><a>Sklep Minecraft</a></li>
            </ul>
            <p class="menu-label">
                Usługi Niestandardowe
            </p>
            <ul class="menu-list">
                <li><a>Brak ;c</a></li>
            </ul>
            <p class="menu-label">
                Dokumentacja
            </p>
            <ul class="menu-list">
                <li><a>Anty Proxy</a></li>
                <li><a>Wykrywacz przekleństw</a></li>
                <li><a>Sklep Minecraft</a></li>
            </ul>
            <p class="menu-label">
                Pomoc
            </p>
            <ul class="menu-list">
                <li>
                    <a>Zgłoszenia</a>
                    <ul>
                        <li><a href="/account/dashboard/ticket/new">Utwórz zgłoszenie</a></li>
                        <li><a class="is-active" href="/account/dashboard/ticket/list/1">Lista zgłoszeń</a></li>
                    </ul>
                </li>
                <li><a href="/account/dashboard/actions/1">Wydarzenia konta</a></li>
            </ul>
        </aside>
    </div>
    <div class="column">
        <div class="container pt-6">
            <div class="box">
                <h1 class="title">Zgłoszenie - $ticket.getId() <span class='closed-$ticket.closed'></span></h1>
                <h3 class="title is-4 has-text-centered">Tytuł: $ticket.topic</h3>
                <div style="width: 100%; max-height: 600px; overflow-y:scroll;" id="messages">
                    <hr>
                    #foreach( $message in $ticket.getMessages() )
                    #if( $ticket.user_id ==  $message.author)
                    <p style="padding: 0.25em; text-align: right;">
                        <img class="image is-32x32" src="/api/avatar/$ticket.user_id" style="display: inline-block; border-radius: 50%; top:10px;">
                        <span class="tag is-medium is-info" style="word-break: break-all;white-space: normal;">$message.message</span>
                    </p>
                    #else
                    <p style="padding: 0.25em; text-align: left; ">
                        <img class="image is-32x32" src="/api/avatar/$ticket.user_id" style="display: inline-block; border-radius: 50%; top:10px;">
                        <span class="tag is-medium is-success" style="word-break: break-all;white-space: normal;">$message.message</span>
                    </p>
                    #end
                    #end
                </div>
                <form class="mt-4" id="new-message">
                    <div class="field has-addons">
                        <div class="control is-expanded">
                            #if( $ticket.closed == 0 )
                                <input type="text" class="input" id="ticket-message-new" placeholder="Napisz swój problem"></div>
                                <div class="control">
                                    <button class="button is-info">Wyślij</button>
                                </div>
                            #else
                                <input type="text" class="input" id="ticket-message-new" placeholder="Napisz swój problem" disabled></div>
                                <div class="control">
                                    <button class="button is-info" disabled>Wyślij</button>
                                </div>
                            #end

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
$footer
<script>
    let last_update = 10;
    $(document).ready(function() {
        $('.closed-0').append('<i class="fas fa-unlock" style="color: green;"></i>');
        $('.closed-1').append('<i class="fas fa-lock" style="color: red;"></i>');
        updateScroll();
        $( "#new-message" ).submit(async function (e) {
            e.preventDefault();
            $.ajax({
                method: "POST",
                url: "http://localhost/api/ticket/addmessage",
                data: {"ticketid": '$ticket.getId()', "message": document.getElementById("ticket-message-new").value}
            }).done(function (response) {
                var returnedData = JSON.parse(response);
                if (returnedData['status'] === 'OK') {
                    updateMessages();
                    $('#ticket-message-new').val('');
                    updateScroll();
                }else{
                    $('#modals').empty();
                    $('#modals').append("<div class=\"modal is-active\">" +
                        "        <div class=\"modal-background\"></div>" +
                        "        <div class=\"modal-card\">" +
                        "            <header class=\"modal-card-head\">" +
                        "                <p class=\"modal-card-title\">Bład przy wysłaniu wiadomości!</p>" +
                        "                <button class=\"delete\" aria-label=\"close\" onclick=\"$('#modals').empty()\"></button>" +
                        "            </header>" +
                        "            <section class=\"modal-card-body\">" +
                        "                <p><strong>Wiadomość nie może być pusta</strong> </p>" +
                        "                <p><strong>DEV-MESSAGE: " + returnedData['message'] + "</strong> </p>" +
                        "            </section>" +
                        "        </div>" +
                        "    </div>")
                }
            });
        });
        setInterval(function(){
            if (last_update <= 0){
                updateMessages();
                last_update = 10;
            }else{
                last_update--;
            }
        }, 1000);
    });

    function updateScroll(){
        $("#messages").animate({ scrollTop: $(document).height() *5 }, "slow");
        return false;
    }

    function updateMessages(){
        $.ajax({
            method:"POST",
            url: "http://localhost/api/ticket/getmessages",
            data: {"ticketid": '$ticket.getId()'}
        }).done(function(response){
            var returnedData = JSON.parse(response);
            if (returnedData['status'] === 'OK') {
                $('#messages').empty();
                $('#messages').append("<hr>");
                $.each(returnedData['data'], function(index, value){
                    if(value['author'] === '$user.getUUID()'){
                        $('#messages').append("<p style='padding: 0.25em; text-align: right; overflow-wrap: normal;'> <img class='image is-32x32' src='/api/avatar/" + value['author'] + "' style='display: inline-block; border-radius: 50%; top:10px;'> <span class='tag is-medium is-info'>" + value['message'] + "</span></p>");
                    }else{
                        $('#messages').append("<p style='padding: 0.25em; text-align: left; overflow-wrap: normal;'> <img class='image is-32x32' src='/api/avatar/" + value['author'] + "' style='display: inline-block; border-radius: 50%; top:10px;'> <span class='tag is-medium is-success'>" + value['message'] + "</span></p>");
                    }
                });
            }
        });
    }
</script>