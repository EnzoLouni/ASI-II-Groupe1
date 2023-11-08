
class PingController {
    constructor({}) {
        console.log(`new PingController`);
    }

    getPong(req, res, next) {
        if (req.query.err) {
            //throw new Error("Ping: Erreur technique !"); // Fonctionne, mais pas top dans un controller.
            return next(new Error("Ping: Erreur technique !"));
        }
        res.end('pong');
    }
}

export default new PingController({});
