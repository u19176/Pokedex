using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace Pokedex_API.Models
{
    public class Pokemon
    {
        [Key]
        public int id_data {get; set;}
        public string? nome_pokemon {get; set;}
        public string? descricao_pokemon {get; set;}
        public string? imagem_pokemon {get; set;}
        public string? icone_pokemon {get; set;}
        public string? tipo1_pokemon {get; set;}
        public string? tipo2_pokemon {get; set;}
    }
}