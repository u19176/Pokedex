using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace Pokedex_API.Models
{
    public class Moves
    {
        [Key]
        public int id_move { get; set; }

        public string? nome_move { get; set; }

        public int? str_move { get; set; }

        public int? acc_move { get; set; }

        public string? tipo_move { get; set; }

        public char? categoria_move { get; set; }
    }
}