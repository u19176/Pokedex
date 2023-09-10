using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Pokedex_API.Models
{
    public class PokemonMoves
    {
        [Key]
        public int Id { get; set; }

        [ForeignKey("Pokemon")]
        public int id_pokemon { get; set; } // Match the foreign key name in the table

        [ForeignKey("Move")]
        public int id_move { get; set; } // Match the foreign key name in the table

        public Pokemon? Pokemon { get; set; }
        public Moves? Move { get; set; }
    }
}
