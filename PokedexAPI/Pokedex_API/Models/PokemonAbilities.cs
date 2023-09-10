using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pokedex_API.Models
{
    public class PokemonAbilities
    {
        [Key]
        public int Id { get; set; }

        [ForeignKey("Pokemon")]
        public int id_pokemon { get; set; } // Match the foreign key name in the table

        [ForeignKey("Ability")]
        public int id_ability { get; set; } // Match the foreign key name in the table

        public Pokemon? Pokemon { get; set; }
        public Abilidades? Ability { get; set; }
    }
}

