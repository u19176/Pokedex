using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Pokedex_API.Models;
using System.Diagnostics.CodeAnalysis;

namespace Pokedex_API.Data
{
    public class PokedexContext: DbContext
    {

        protected readonly IConfiguration Configuration;

        public PokedexContext(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        protected override void OnConfiguring(DbContextOptionsBuilder options)
        {
            options.UseSqlServer(Configuration.GetConnectionString("StringConexaoSQLServer"));
        }

        public DbSet<Pokemon>? pokemon_data {get; set;}
        public DbSet<Abilidades>? abilidades {get; set;}
        public DbSet<Moves>? moves {get; set;}
        public DbSet<PokemonAbilities>? pokemon_abilities { get; set; }
        public DbSet<PokemonMoves>? pokemon_moves { get; set; }


    }
}