using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace Pokedex_API.Models
{
    public class Abilidades
    {
         [Key]
        public int id_ability { get; set; }

        public string? nome_abilidade { get; set; }
        public string? descricao_abilidade { get; set; }
    }
}