select * from dbo.pokemon_data

https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png
https://icon-library.com/images/2018/7081132_charmander-sprite-pixel-art-pokemon-charmander-transparent-png.png

https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png
https://image.pngaaa.com/819/3998819-middle.png

https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png
https://art.pixilart.com/f97126d7552fd3d.png

select
    'data source=' + @@servername +
    ';initial catalog=' + db_name() +
    case type_desc
        when 'WINDOWS_LOGIN' 
            then ';TrustServerCertificate=True'
        else
            ';user id=' + suser_name() + ';password=<<YourPassword>>'
    end
    as ConnectionString
from sys.server_principals
where name = suser_name()

