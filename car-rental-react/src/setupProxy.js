const { legacyCreateProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api/auth',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9090',
            changeOrigin: true,
            logLevel: 'debug',
        })
    );    app.use(
        '/api/reservation',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9091',
            logLevel: 'debug',
            changeOrigin: true,
        })
    );
};