const Login = { template: '<login></login>' }
const Homepage = { template : '<homepage></homepage>' }
const AdministratorKlinike = { template : '<administratorKlinike></administratorKlinike>' }
const Sala = { template : '<sala></sala>' }
const TipPregleda = { template : '<tipPregleda></tipPregleda>' }
const Register = { template : '<register></register>' }
const Lekar = { template : '<lekar></lekar>' }
const Klinika = { template: '<klinika></klinika>' }
const Lekari = { template: '<lekari></lekari>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { 	path: '/',
	    	redirect : {
	    		name : "login"
	    	}
	    },
	    {
	    	path: "/login",
	    	name : "login",
	    	component : Login
	    },
	    {
	    	path: "/register",
	    	name : "register",
	    	component : Register
	    },
	    {
	    	path: "/homepage",
	    	name : "homepage",
	    	component : Homepage
	    },
	    {
	    	path: "/administratorKlinike",
	    	name : "administratorKlinike",
	    	component : AdministratorKlinike
	    },
	    {
	    	path: "/sala",
	    	name : "sala",
	    	component : Sala
	    },
	    {
	    	path: "/lekar",
	    	name : "lekar",
	    	component : Lekar
	    },
	    {
	    	path: "/tipPregleda",
	    	name : "tipPregleda",
	    	component : TipPregleda
	    },
	    {
	    	path: "/klinika",
	    	name : "klinika",
	    	component : Klinika
	    },
	    {
	    	path: "/lekari",
	    	name : "lekari",
	    	component : Lekari
	    }
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	data() {
        return {
            authenticated: false,
        }
    },
    mounted() {
        if(!this.authenticated) {
            this.$router.replace({ name: "login" });
        }
    },
    methods: {
        setAuthenticated(status) {
            this.authenticated = status;
        },
        logout() {
            this.authenticated = false;
        }
    }
});

