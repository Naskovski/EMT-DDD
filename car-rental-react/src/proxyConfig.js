const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function(app) {
    app.use(createProxyMiddleware('/api/reservation', { target: 'http://localhost:9091' }));
};
