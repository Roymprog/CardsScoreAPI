// Authenticate against cards_scores db
conn = new Mongo();
db = conn.getDB("cards_scores");
db.auth('root', 'example');

// Create user for api
db.createUser(
        {
            user: "cardsscoreapi",
            pwd: "cardsscoreapisecret",
            roles: [
                {
                    role: "readWrite",
                    db: "cards_scores"
                }
            ]
        }
);