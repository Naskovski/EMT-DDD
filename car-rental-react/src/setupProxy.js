const { legacyCreateProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api/auth',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9090',
            changeOrigin: true,
            logLevel: 'debug',
        })
    );
    app.use(
        '/api/client',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9090',
            changeOrigin: true,
            logLevel: 'debug',
        })
    );
    app.use(
        '/api/reservation',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9091',
            logLevel: 'debug',
            changeOrigin: true,
        })
    );
    app.use(
        '/api/location',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9093',
            logLevel: 'debug',
            changeOrigin: true,
        })
    );
    app.use(
        '/api/vehicle',
        legacyCreateProxyMiddleware({
            target: 'http://localhost:9094',
            logLevel: 'debug',
            changeOrigin: true,
        })
    );
};