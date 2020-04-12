const Login = { template: '<login></login>' }
const Homepage = { template : '<homepage></homepage>' }
const AdministratorKlinike = { template : '<administratorKlinike></administratorKlinike>' }
const Sala = { template : '<sala></sala>' }
const TipPregleda = { template : '<tipPregleda></tipPregleda>' }
const Register = { template : '<register></register>' }
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
	    	path: "/tipPregleda",
	    	name : "tipPregleda",
	    	component : TipPregleda
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

