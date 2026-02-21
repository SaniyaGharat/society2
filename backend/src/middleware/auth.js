// Middleware to mock check user role from headers
// In production, this would verify a JWT token
const checkRole = (allowedRoles) => {
    return (req, res, next) => {
        // For demo: client sends 'x-user-role' header
        // e.g. 'ADMIN', 'TREASURER', 'RESIDENT'
        const role = req.headers['x-user-role'] || 'RESIDENT'; // Default to lowest access

        if (allowedRoles.includes(role)) {
            next();
        } else {
            res.status(403).json({ msg: `Access Denied. Required role: ${allowedRoles.join(' or ')}` });
        }
    };
};

module.exports = {
    checkRole
};
