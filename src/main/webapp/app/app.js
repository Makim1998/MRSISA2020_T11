const Login = { template: '<login></login>' }
const Homepage = { template : '<homepage></homepage>' }
const AdministratorKlinike = { template : '<administratorKlinike></administratorKlinike>' }
const Sala = { template : '<sala></sala>' }
const TipPregleda = { template : '<tipPregleda></tipPregleda>' }
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

