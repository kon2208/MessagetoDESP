function karateConfig() {
    let env = karate.env; // get system property 'karate.env'
    karate.log('karate.env system property was:', env);

    if (!env) {
        env = 'test';
    }

    if (karate.properties['demo.server.https'] === 'true') {
        protocol = 'https';
        karate.configure('ssl', true);
    }


    // Configuration object for environment-specific settings
    var config = {
        baseURL: 'https://customer-platform.central-dev-cust.kpsazc.dgtl.kroger.com',
        groupsGetEndpoint: "/groups/v1/groups"

    }

    // Setting up Kafka consumer details based on the environment
    if (env === 'dev') {
        config.baseURL = 'https://customer-platform.central-dev-cust.kpsazc.dgtl.kroger.com';
        groupsGetEndpoint: "/groups/v1/groups";


    } else if (env === "test") {
        config.baseURL=  'https://customer-platform.central-test-cust.kpsazc.dgtl.kroger.com';
        groupsGetEndpoint: "/groups/v1/groups";


    } else if (env === "stage_central") {
        config.baseURL=  'https://customer-platform.central-stage-cust.kpsazc.dgtl.kroger.com';
        groupsGetEndpoint: "/groups/v1/groups";

    } else if (env === "stage_east") {
        config.baseURL=  'https://customer-platform.east-stage-cust.kpsazc.dgtl.kroger.com';
        groupsGetEndpoint: "/groups/v1/groups";

    }

    return config;
}