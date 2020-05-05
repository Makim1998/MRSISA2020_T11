const Login = { template: '<login></login>' }
const Homepage = { template : '<homepage></homepage>' }
const AdministratorKlinike = { template : '<administratorKlinike></administratorKlinike>' }
const Pacijent = { template: '<pacijentHome></pacijentHome>' }
const AdministratorCentra = { template: '<administratorCentra></administratorCentra>' }
const MedicinskaSestra = { template: '<MSHome></MSHome>' }
const Register = { template : '<register></register>' }
const Lekar = { template : '<lekar></lekar>' }

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
	    	path: "/MSHome",
	    	name : "MSHome",
	    	component : MedicinskaSestra
	    },
	    {
	    	path: "/administratorCentra",
	    	name : "administratorCentra",
	    	component : AdministratorCentra
	    },
	    {
	    	path: "/lekar",
	    	name : "lekar",
	    	component : Lekar
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

