db.createUser(
    {
        user:"sebs",
        pwd:"clave",
        roles:[{
            role:"readWrite",
            db:"arep"
        }]
    }
)