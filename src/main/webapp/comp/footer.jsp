<style>
    /* footer goes here -------------------------------------------------------------*/
    .footer {
        z-index: 9999;
        /*--footer-background: linear-gradient(to right,  #ECF5EE , rgb(62, 232, 255));*/ /*old design*/
        background-color: appworkspace;
        display: grid;
        position: relative;
        grid-area: footer;
        min-height: 12rem;
    }

    .footer .content2 {
        z-index: 2;
        display: grid;
        grid-template-columns: 1fr auto;
        grid-gap: 4rem;
        padding: 2rem;
        background: var(--footer-background);
    }

    .footer .content2 a, .footer .content2 p {
        color: #042141;
        text-decoration: none;
    }

    .footer .creators a {
        font-size: 0.75rem;
        font-weight: bold;
    }

    .footer .content2 b {
        font-size: 0.75rem;
        color: black;
    }

    .footer .content2 p {
        margin: 0;
        font-size: 0.75rem;
    }

    .footer .content2 > div {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .footer .content2 > div > div {
        margin: 0.25rem 0;
    }

    .footer .content2 > div > div > * {
        margin-right: 0.5rem;
    }

    .footer .content2 > div .image {
        align-self: center;
        width: 4rem;
        height: 4rem;
        margin: 0.25rem 0;
        background-size: cover;
        background-position: center;
    }

    @keyframes bubble-size {
        0%, 75% {
            width: var(--size, 4rem);
            height: var(--size, 4rem);
        }
        100% {
            width: 0rem;
            height: 0rem;
        }
    }

    @keyframes bubble-move {
        0% {
            bottom: -4rem;
        }
        100% {
            bottom: var(--distance, 10rem);
        }
    }

    /*footer ends here =========================================================*/
</style>
<div class="footer">
    <div class="content2">
        <div>
            <div class="creators">
                <b>CREATORS: </b>
                <a href="https://www.instagram.com/komar112011/" target="_blank">OMAR</a>
                <a href="https://www.instagram.com/wzmktn/" target="_blank">AZIM</a>
                <a href="https://www.instagram.com/garylimkz/" target="_blank">GARY</a>
            </div>
            <div>
                <a href="https://ftkki.umt.edu.my" target="_blank"><b>UNIVERSITY MALAYSIA TERENGGANU: UMT</b></a>
            </div>
            <div>
                <a href="mailto:faizah_aplop@umt.edu.my;"><b>DR. FAIZAH BINTI APLOP</b></a>
            </div>
            <div>
                <a href="../etc/support.jsp"><b>SUPPORT</b></a>
                <a href="../etc/about.jsp"><b>ABOUT-US</b></a>
                <a href="#faq.jsp"><b>FAQ</b></a>
            </div>
        </div>
        <div>
            <a class="image"
               href="/home"
               style="
               background-image: url('https://upload.wikimedia.org/wikipedia/commons/3/3e/Logo_Rasmi_UMT.png' )"></a>
            <p>©2023 UMT Citawarisan</p>
        </div>
    </div>
</div>
<svg style="position: fixed; top: 100vh">
    <defs>
    </defs>
</svg>

