const Login = { template: '<login></login>' }
const Homepage = { template : '<homepage></homepage>' }
const AdministratorKlinike = { template : '<administratorKlinike></administratorKlinike>' }
const Pacijent = { template: '<pacijentHome></pacijentHome>' };
const SdministratorCentra = { template: '<administratorCentra></administratorCentra>' };
const Sala = { template : '<sala></sala>' }
const TipPregleda = { template : '<tipPregleda></tipPregleda>' }
const Register = { template : '<register></register>' }
const Lekar = { template : '<lekar></lekar>' }
const Klinika = { template: '<klinika></klinika>' }
const bus = new Vue();

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
	    	path: "/pacijent",
	    	name : "pacijentHome",
	    	component : Pacijent
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
        	console.log("Not logged in!");
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

