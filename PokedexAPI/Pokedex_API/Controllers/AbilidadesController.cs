using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Pokedex_API.Data;
using Pokedex_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Pokedex_API.Controllers
{
    [ApiController]
    [Route("api/abilidades")]
    public class AbilidadesController : ControllerBase
    {
        private readonly PokedexContext _context;

        public AbilidadesController(PokedexContext context)
        {
            _context = context;
        }

        [HttpGet("/getabilidades")]
        public async Task<ActionResult<IEnumerable<Abilidades>>> GetAbilidades()
        {
            return await _context.abilidades.ToListAsync();
        }

        [HttpGet("/getab/{id}")]
        public async Task<ActionResult<Abilidades>> GetAbilidade(int id)
        {
            var abilidade = await _context.abilidades.FindAsync(id);

            if (abilidade == null)
            {
                return NotFound();
            }

            return abilidade;
        }

        [HttpPost]
        [Route("/createab")]
        public async Task<ActionResult<Abilidades>> PostAbilidade(Abilidades abilidade)
        {
            _context.abilidades.Add(abilidade);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAbilidade", new { id = abilidade.id_ability }, abilidade);
        }

        [HttpPut("/updateab/{id}")]
        public async Task<IActionResult> PutAbilidade(int id, Abilidades abilidade)
        {
            if (id != abilidade.id_ability)
            {
                return BadRequest();
            }

            _context.Entry(abilidade).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AbilidadeExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return Ok();
        }

        [HttpDelete("/deleteab/{id}")]
        public async Task<IActionResult> DeleteAbilidade(int id)
        {
            var abilidade = await _context.abilidades.FindAsync(id);
            if (abilidade == null)
            {
                return NotFound();
            }

            _context.abilidades.Remove(abilidade);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool AbilidadeExists(int id)
        {
            return _context.abilidades.Any(e => e.id_ability == id);
        }
    }
}
